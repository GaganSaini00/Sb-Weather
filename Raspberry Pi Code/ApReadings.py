import board
import busio
import adafruit_dps310
import time
from time import sleep
import pyrebase
import random

i2c_ch = 1

# TMP102 address on the I2C bus
i2c_address = 0x77

# Register addresses
reg_temp = 0x00
reg_config = 0x01


# configuration of the database access
# Global objects: config and user
# ====================================================================================================
config = {
    "apiKey": "AIzaSyDOSceYn1BevJ69eBhzodQiDmTRIHt7RPw",
    "authDomain": "sunshine-boys-app.firebaseapp.com",
    "databaseURL": "https://sunshine-boys-app.firebaseio.com/",
    "storageBucket": "gs://sunshine-boys-app.appspot.com/",
}

user = None  # define user as a global variable to access from all the functions.
# ====================================================================================================

# The authorization function
# ====================================================================================================
def GetAuthorized(firebase):
    global user
    auth = firebase.auth()  # Get a reference to the auth service
    # authenticate a user
    try:
#        user = auth.sign_in_with_email_and_password("abcde@gmail.com",
#                                                    "ABc123Mt5")  # username and password of your account for database
        user = auth.sign_in_with_email_and_password("sbweathersensordatabase@gmail.com",
                                                    "Sbweathersensordatabase!")  # username and password of your account for database
        print(user)  # display the user information, if successful
    except:
        print("Not authorized")
        user = None

# The function to initialize the database.
# ====================================================================================================
def dbInitialization():
    firebase = pyrebase.initialize_app(config)  # has to initialize the database
    GetAuthorized(firebase)  # get authorized to operate on the database.
    return firebase

# The function to get the data from firebase database.
# ====================================================================================================
def GetDatafromFirebase(db):
    results = db.child("NathanSensor").get(user["idToken"]).val();  # needs the authorization to get the data.
    print("These are the records from the Database")
    print(results)
    return;


# The function to send the data to firebase database.
# ====================================================================================================
def sendtoFirebase(db, sensordata):
    result = db.child("NathanSensor").push(sensordata, user["idToken"])  # needs the authorization to save the data
    print(result)
    return;

# The function to send the data to firebase database's user authorized section.
# Each user has a separate record tree, and it is only accessible for the authorized users.
# ====================================================================================================
def sendtoUserFirebase(db, sensordata):
    userid = user["localId"] # this will guarantee the data is stored into the user directory.
    result = db.child("NathanUserdata").child(userid).push(sensordata, user["idToken"])  # needs the authorization to save the data
    print(result)
    return;

# The function to set up the record structure to be written to the database.
# ====================================================================================================
def setupData(air_pressure, timestamp):
    sensor = {"Airpressure": air_pressure,
              "Timestamp": timestamp}  # always post the timestamps in epoch with the data to track the timing.
                                        # Store the data as the dictionary format in python  # refer to here:
                                        # https://www.w3schools.com/python/python_dictionaries.asp
    return sensor
    

firebase=dbInitialization()    

i2c = busio.I2C(board.SCL, board.SDA)
 
dps310 = adafruit_dps310.DPS310(i2c) 

sensordata = setupData(dps310.pressure,int(time.time()))
def abc():
 dps310 = adafruit_dps310.DPS310(i2c) 

def writeDb(dps310):
 sendtoFirebase(firebase.database(), sensordata)  # save to the public access data tree
 sendtoUserFirebase(firebase.database(), sensordata) # save to the user specific userdata tree
 GetDatafromFirebase(firebase.database());  # this statement is outside the while loop
 
while True:
    writeDb(dps310)
    print("Pressure = %.1f hPa" % dps310.pressure)
    print("")
    time.sleep(3)
    
