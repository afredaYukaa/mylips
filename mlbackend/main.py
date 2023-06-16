from flask import Flask , jsonify , request
from PIL import Image
from model import prediction
from bucket import get_image_url
import io

app = Flask(__name__)

@app.route('/api/upload', methods = ['POST'])
def api_upload():
    filename = request.form.get('filename')
    uploaded_file = request.files.get('file')

    if not uploaded_file and not filename :
        resp = jsonify({'message' : 'No file part or file name in the request '})
        resp.status_code = 400

    else :
        image_bytes = uploaded_file.read()
        color = prediction(image_bytes)
        result = get_image_url(color)
        # resp = jsonify({'recommend lips color' : result})
        resp = jsonify({'error' : 'false' , 'message': "success", 'color' : color ,'ListColor' : result })
        resp.status_code = 201

    return resp

if __name__ == '__main__':
    app.run(debug=True , port='8080')

else:
    gunicorn_app = app