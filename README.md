# Puzzle Clock

The puzzle clock is an alarm clock app with a twist! Users are able to set a time in which an alarm will trigger. When the 
alarm goes off, users are greeted with a puzzle they must solve in order to shut off the alarm. The idea behind this is this will allow the user to get a grip
on their mental and physicall cordnation by engaging with a short puzzle. The puzzle consists of 4 shapes that flash in a random sequence. The user is then required to repeate the order of the shapes to shut off the alarm.

## Figma
![Puzzle Clock Wireframe (2)](https://github.com/user-attachments/assets/fe2e0192-de46-4fcf-bcfc-49fd093d38a5)

## Tech Spec
In order to handle the persistant storage of user alarms between app session, we are using DataStore. The acutal alarm logic is a simple intent broadcaster/reciever that creates that app intent to start a 
new activity(the puzzle alarm) at the scheduled alarm time. The alarm sound playing is made possible with a simple media player object and audio file stored in the resources section of the project.

## Minimum Deployment
Target Android API is 31.
