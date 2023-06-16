# MyLips
C23-PC656 - Bangkit 2023 Captone Project

## Team Member 
| Name	| Bangkit-ID	| Path |
| ---  | ---  | --- |
| Nasya Aghita Riona | M017DKY3918  | Machine Learning |
| Nashira Syifa Salsabila | M151DSY1466  | Machine Learning |
| Tiani Ayu Lestari | M302DSY0111  | Machine Learning |
| Jose Rafael | C037DKX4163  | Cloud Computing |
| Afreda Yuka Suherman | C148DSX3564  | Cloud Computing |
| Julius Kevin Raharjo | A212DKX4107  | Mobile Development |

## About The Project
<div style="text-align: justify">The project that we are about to make is “My Lips”. My Lips is perfect for beginners who are just learning about makeup, have no one to consult or shy to consult others, because My Lips helps you recommend the selection of colors and types of lipstick that you can apply based on your skin tone so that people who want to start applying make up or find it difficult to match their skin tone take less time to try all colours by using My Lips. What makes our product unique is that our application will use face recognition to detect the users lips and analyze the users skin tone to recommend which color of lipstick is suitable with users skin tone, this will be much easier for the user to know their skin tone as they will not have to do any manual test or ask others to find what their skin tone color is.</div>

## Installation
To install the application, download the APK from [MyLips.apk](https://github.com/afredaYukaa/mylips/blob/main/MyLips.apk)



# Machine Learning
This project is built using Deep Learning Technology with Convolutional Neural Network algorithm. Here is the library that was used in this project.

- Python 3.10
- TensorFlow 2.12.0
- NumPy 1.22.4
- Matplotlib 3.7.1

## Dataset

We gathered our dataset manualy from various sources. The skin tone we use are divided into 6 colors:
- Brown Caramel
- Cacao Black Skin
- Fair
- Light Medium 
- Dark Medium 

You can download our Dataset using the following link:
- Skin Tone: [Link](https://drive.google.com/file/d/1aTdDH-M_G0ukOrk7NOKLzCYcXWzgqGRT)
- Lipstick Color: [Link](https://drive.google.com/file/d/1dYU4EqqLljnqWNBBeXYZ_LvBJRfcTkXA/view?usp=sharing)


# Mobile Development
MyLips is a mobile app developed using kotlin. It provides a lipstick color recommendation based on users uploaded photo

This project utilizes the following libraries:

- [Glide](https://github.com/bumptech/glide): A fast and efficient image loading and caching library.
- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for making API calls.
- [CameraX](https://developer.android.com/training/camerax): The CameraX library provides a simplified and consistent API for accessing the camera capabilities on Android devices.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding): ViewBinding is a feature in Android Studio that allows for more efficient and type-safe access to views in the layout files.

 # Cloud Computing
Start a cloud environment using compute engine and SQL virtual machine instances, set up networking for server to mobile apps, debug machine learning implementations in Cloud Run. Conclusion: In this capstone, we have created an app called MY Lips to recommend users' lip colors. As a backend, the Google Cloud Platform service connects android applications to SQL databases, Cloud Run services and Compute Engine.


  Cloud build & deploy

gcloud builds submit --tag gcr.io/my-lips-387014/api_upload .

gcloud run deploy --image gcr.io/my-lips-387014/api_upload

