import time
import smbus
from time import sleep
import pyrebase
import random

i2c_ch = 1

# TMP102 address on the I2C bus
i2c_address = 0x48

# Register addresses
reg_temp = 0x00
reg_config = 0x01


#/////////////////////////////////////////////////////////////////////////////////////////////////////
#DATABASE CODE
#/////////////////////////////////////////////////////////////////////////////////////////////////////

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
    results = db.child("GaganSensor").get(user["idToken"]).val();  # needs the authorization to get the data.
    print("These are the records from the Database")
    print(results)
    return;


# The function to send the data to firebase database.
# ====================================================================================================
def sendtoFirebase(db, sensordata):
    result = db.child("GaganSensor").push(sensordata, user["idToken"])  # needs the authorization to save the data
    print(result)
    return;


# The function to send the data to firebase database's user authorized section.
# Each user has a separate record tree, and it is only accessible for the authorized users.
# ====================================================================================================
def sendtoUserFirebase(db, sensordata):
    userid = user["localId"]  # this will guarantee the data is stored into the user directory.
    result = db.child("GaganUserdata").child(userid).push(sensordata,
                                                     user["idToken"])  # needs the authorization to save the data
    print(result)
    return;


# The function to set up the record structure to be written to the database.
# ====================================================================================================
def setupData(temperature, timestamp):
    sensor = {"Temperature": temperature,
              "Timestamp": timestamp}  # always post the timestamps in epoch with the data to track the timing.
    # Store the data as the dictionary format in python  # refer to here:
    # https://www.w3schools.com/python/python_dictionaries.asp
    return sensor

#/////////////////////////////////////////////////////////////////////////////////////////////////////
#SENSOR CODE
#/////////////////////////////////////////////////////////////////////////////////////////////////////

# Calculate the 2's complement of a number
def twos_comp(val, bits):
    if (val & (1 << (bits - 1))) != 0:
        val = val - (1 << bits)
    return val

# Read temperature registers and calculate Celsius
def read_temp():

    # Read temperature registers
    val = bus.read_i2c_block_data(i2c_address, reg_temp, 2)
    # NOTE: val[0] = MSB byte 1, val [1] = LSB byte 2
    #print ("!shifted val[0] = ", bin(val[0]), "val[1] = ", bin(val[1]))

    temp_c = (val[0] << 4) | (val[1] >> 4)
    #print (" shifted val[0] = ", bin(val[0] << 4), "val[1] = ", bin(val[1] >> 4))
    #print (bin(temp_c))

    # Convert to 2s complement (temperatures can be negative)
    temp_c = twos_comp(temp_c, 12)

    # Convert registers value to temperature (C)
    temp_c = temp_c * 0.0625

    return temp_c

# Initialize I2C (SMBus)
bus = smbus.SMBus(i2c_ch)

# Read the CONFIG register (2 bytes)
val = bus.read_i2c_block_data(i2c_address, reg_config, 2)

print("\nGagandeep Singh Saini N01305833\n")

print("Old CONFIG:", val)


# Set to 4 Hz sampling (CR1, CR0 = 0b10)
val[1] = val[1] & 0b00111111
val[1] = val[1] | (0b10 << 6)

# Write 4 Hz sampling back to CONFIG
bus.write_i2c_block_data(i2c_address, reg_config, val)

# Read CONFIG to verify that we changed it
val = bus.read_i2c_block_data(i2c_address, reg_config, 2)

print("New CONFIG:", val)

def writetodb():
    #temperature2 = read_temp()
    sensordata = setupData(temperature, #temperature                 
                            int(time.time()))  # this is the epoch time for this record.
    sendtoFirebase(firebase.database(), sensordata)  # save to the public access data tree
    sendtoUserFirebase(firebase.database(), sensordata)  # save to the user specific userdata tree

# Print out temperature every 3 second
while True:
    temperature = round(read_temp(),0)
    print("\nTemperature:",temperature, "C")
    firebase = dbInitialization()
    writetodb()
    time.sleep(3)

