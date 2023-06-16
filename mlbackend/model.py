from tensorflow.keras.models import load_model
import numpy as np
import cv2
import imutils
import os

path = 'final_model.h5'
model =load_model(path)

def prediction(img):
    nparr = np.frombuffer(img, np.uint8)
    image = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    image = imutils.resize(image, width=400)
    img = cv2.resize(image,(150,150))
    x = np.asarray(img)
    x = x/255
    x=np.expand_dims(x, axis=0)
    images = np.vstack([x])
    classes = model.predict(images, batch_size=10)
    index_max = np.argmax(classes)
    output = ''
    arr_color = ['Browned Caramel','Cocoa Black Skin','Espresso Milk Chocolate','Fair','Dark Medium']
    if index_max>-1 and index_max <5:
        output = arr_color[index_max]
    else:
        output ="Light Med"
    
    return output
