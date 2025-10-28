import Adafruit_DHT
import RPi.GPIO as GPIO
import time

sensor = Adafruit_DHT.DHT11
pin = 4           # DHT11 connected to GPIO4
led = 17          # LED pin
threshold = 35.0  # Celsius

GPIO.setmode(GPIO.BCM)
GPIO.setup(led, GPIO.OUT)

while True:
    humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
    if temperature is not None:
        print(f"Temp={temperature:.1f}°C  Humidity={humidity:.1f}%")
        if temperature > threshold:
            GPIO.output(led, GPIO.HIGH)
            print("ALERT! High Temperature")
        else:
            GPIO.output(led, GPIO.LOW)
    else:
        print("Sensor read failed")
    time.sleep(2)





sudo apt-get install git-core



git clone https://github.com/adafruit/Adafruit_Python_DHT.git
cd Adafruit_Python_DHT
sudo apt-get install build-essential python-dev
sudo python setup.py install

