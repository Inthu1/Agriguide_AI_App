import numpy as np

from flask import Flask, request

import os
os.environ['TF_ENABLE_ONEDNN_OPTS'] = '0'

import tensorflow as tf

app = Flask(__name__)

crop_list = ['rice', 'maize', 'chickpea', 'kidneybeans', 'pigeonpeas', 'mothbeans', 'mungbean', 'blackgram', 'lentil',
             'pomegranate', 'banana', 'mango', 'grapes', 'watermelon', 'muskmelon', 'apple', 'orange', 'papaya',
             'coconut', 'cotton', 'jute', 'coffee']
model = tf.keras.models.load_model('./CropRecomendation.h5')
fert_list = ['DAP', 'UREA', 'Twenty-Twenty', 'Twenty Eight-Twenty Eight']
data = tf.keras.models.load_model('./fertilizer_recomendation.h5')


@app.route('/recomend_crop')
def recomendCrop():
    N = float(request.args.get('N', 0.0))
    P = float(request.args.get('P', 0.0))
    K = float(request.args.get('K', 0.0))
    temp = float(request.args.get('temperature', 0.0))
    hum = float(request.args.get('humidity', 0.0))
    ph = float(request.args.get('ph', 0.0))
    rf = float(request.args.get('rainfall', 0.0))
    prediction = model.predict(np.array([[N, P, K, temp, hum, ph, rf]]))
    return crop_list[prediction[0].tolist().index(max(prediction[0]))]


@app.route('/recommend_fertilizer')
def fertilizer():
    Nitrogen = float(request.args.get('Nitrogen', 0.0))
    Potassium = float(request.args.get('Potassium', 0.0))
    Phosphorous = float(request.args.get('Phosphorous', 0.0))
    prediction = data.predict(np.array([[Nitrogen, Potassium, Phosphorous]]))
    return fert_list[prediction[0].tolist().index(max(prediction[0]))]


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=3000)


# Run ipconfig cmd in terminal
# Take IPv4 Address from [Wireless LAN adapter Local Area Connection* 2:] section
# Use the IP in android code.