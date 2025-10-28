import RPi.GPIO as GPIO
import time

led = 8
GPIO.setmode(GPIO.BOARD)
GPIO.setup(led, GPIO.OUT, initial=0)

try:
    while True:
        GPIO.output(led, GPIO.HIGH)
        print("ON")
        time.sleep(1)
        GPIO.output(led, GPIO.LOW)
        print("OFF")
        time.sleep(1)
except KeyboardInterrupt:
    GPIO.cleanup()
    print("Exiting...")




//c
void setup() {
  pinMode(13, OUTPUT); // LED
  pinMode(9, INPUT);   // IR Sensor
  Serial.begin(9600);
}

void loop() {
  if (digitalRead(9) == LOW) { // Object detected
    digitalWrite(13, HIGH);
    delay(10);
  } else {
    digitalWrite(13, LOW);
    delay(10);
  }
}
