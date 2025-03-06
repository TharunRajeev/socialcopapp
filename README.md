# PotholeUserAndroidApp
This is repository for Pothole User Android app

This is one of the components of a complete Pothole App 

The complete understanding of Pothole app could be found over here []

### Repo Links to other components : 

- [Pothole User Android App (Current)](https://github.com/TharunRajeev/socialcopapp)
- [Pothole User Server (Private)](https://github.com/TharunRajeev/socialcopserver)
- [Pothole Civil Authority Android App](https://github.com/TharunRajeev/SocialCop-CivilAuthoritiesApp)
- [Pothole Civil Authority Server (Private)](https://github.com/TharunRajeev/SocialCop-CivilAuthoritiesAppServer)

This app is the frontend interface for User.

This app communicates with the Node js / Express backend server , and talks to it via set of APIs 


## Features of this app
0) Signup / Signin Authentication.
1) View and track status of Potholes reported by the user
2) Filter Potholes based on current status  (Resolved , Assigned  , Rejected , etc)
3) Uploading Pothole : Image , description , location , etc . the concerned civil authority will be updated accordingly automatically.
4) One to Many Communication between Authority and User , Authority can update the Pothole status , so that user can get notified and track the status of Potholes too .
5) The app have notifications features and email updates too.
6) There are more technological details and security factors too , I won't go into implementation detail over here.

## Technologies Used

Android (Java) , Retrofit , Glide , etc.


#### NOTE :
> Replace the BASE_URL in strings , with your server url.

## Screenshots
<img src="https://github.com/TharunRajeev/socialcopapp/blob/first_commit/Screenshot1.png" align="left" width="30%" height="auto" alt="Screenshot of App"/>
<img src="https://github.com/TharunRajeev/socialcopapp/blob/first_commit/Screenshot2.png" width="30%" height="auto" alt="Screenshot of App"/>
Watch the video here : [https://drive.google.com/file/d/1nK9rvR2pSUdCnShSxSHXVsypJCSNE8WD/view?usp=sharing]
> There are few fixes that are needed to be done , so before using it into production make sure of some fixes.
