# Plant_Specie_And_Disease_Detection
Using Deep Learning model to predict disease of plant by using Plant Leaf as input.

# About the Project

Using Convolutional Neural Network deep learning model to train [Plant Village Dataset](https://www.kaggle.com/akshay224/majorprojectdataset) 
dataset to detect plant is healty or infected with some disease, there are 38 specie-disease classes and 1 random class.
Model is based on Alexnet deep learning architecture. There are more than 100K images which is split into two sets training and validation set.
Model testing accuracy is 81.8%.

# Deployment
1. CNN model is saved as tensorflow lite model so that it can be used in mobile devices.
2. Using android application live image of plant leaf is taken and prediction is done as if plant is healty or infection with some disease.


## [![Deep Learning Keras Model](https://drive.google.com/file/d/1XI4SQ7QTmdZKuM4nHEk2IB4uA9eo1MUY/view?usp=sharing)](https://www.kaggle.com/akshay224/major-project)

* To Do. 
1. Save your Keras Model as tensorflow lite format i.e .tflite as tf_liteModel.tflite
2. Create a text file named plant_labels.txt and add your dataset's target classes line by line
3. Paste both these files in app/src/main/assets directory


 
