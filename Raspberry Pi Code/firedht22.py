import Adafruit_DHT
import time
from time import sleep
import pyrebase
 
DHT_SENSOR = Adafruit_DHT.DHT22
DHT_PIN = 19
 
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
    email = "sbweathersensordatabase@gmail.com"
    password = "Sbweathersensordatabase!"
    # authenticate a user
    try:
        user = auth.sign_in_with_email_and_password(email,
                                                    password)  # username and password of your account for database
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
    results = db.child("Jeremy's Sensor").get(user["idToken"]).val();  # needs the authorization to get the data.
    print("These are the records from the Database")
    print(results)
    return;


# The function to send the data to firebase database.
# ====================================================================================================
def sendtoFirebase(db, sensordata):
    result = db.child("Jeremy's Sensor").push(sensordata, user["idToken"])  # needs the authorization to save the data
    print(result)
    return;

# The function to send the data to firebase database's user authorized section.
# Each user has a separate record tree, and it is only accessible for the authorized users.
# ====================================================================================================
def sendtoUserFirebase(db, sensordata):
    userid = user["localId"] # this will guarantee the data is stored into the user directory.
    result = db.child("Jeremy's Userdata").child(userid).push(sensordata, user["idToken"])  # needs the authorization to save the data
    print(result)
    return;

# The function to set up the record structure to be written to the database.
# ====================================================================================================
def setupData(humidity, timestamp):
    sensor = {
               "Humidity":humidity,
              "timestamp": timestamp}  # always post the timestamps in epoch with the data to track the timing.
    # Store the data as the dictionary format in python  # refer to here:
    # https://www.w3schools.com/python/python_dictionaries.asp
    return sensor
firebase = dbInitialization()
humidity, temperature = Adafruit_DHT.read(DHT_SENSOR, DHT_PIN)
sensordata = setupData(humidity,
                           int(time.time()))
def writedb(humidity):
    
        sendtoFirebase(firebase.database(), sensordata)  # save to the public access data tree
sendtoUserFirebase(firebase.database(), sensordata) # save to the user specific userdata tree
GetDatafromFirebase(firebase.database());  # this statement is outside the while loop


 
def main():

    while True:
        roundhumid = round(humidity,1)
        writedb(roundhumid);
        time.sleep(5)
    if humidity is not None:
        print("Humidity={1:0.1f}%".format(humidity))
        
        
    else:
        print("Sensor failure. Check wiring.");
        time.sleep(5);
    




    
if __name__=="__main__":
   main()
      

    
    
