from google.cloud import storage
bucket_name = 'mylips'
import json

# Authenticate ourselves using the service account private key
path_to_private_key = 'your-credential.json'
client = storage.Client.from_service_account_json(json_credentials_path=path_to_private_key)
bucket = storage.Bucket(client, bucket_name)

def into_link(url) :
    return 'https://storage.googleapis.com/mylips/'+ url.replace(" ", "%20")

def into_name(color , name) :
    name = name.replace(color,'')
    name = name.replace('LipstickColor//','')
    return name.replace('.jpg','')

def get_image_url(color) :
    str_folder_name_on_gcs = f'LipstickColor/{color}/'
    blobs = bucket.list_blobs(prefix=str_folder_name_on_gcs)
    res = {str(into_name(color ,i.name)): str(into_link(i.name) ) for i in blobs}
    result = []
    for k, v in res.items():
        result.append({'name': k, 'link': v})

    return result
