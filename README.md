# Kiddom

Writeup

Integrated Development Environment (IDE): Android Studio
Programming Language: JAVA
Platform: Android(4.4 and up)


Video Demo: https://www.youtube.com/watch?v=YYZCR3i6CkY


Search Screen:

The API is integrated using Retrofit.
Each list item will show Name, Description, Article, Image if available.
The button WIKI on each item view will redirect the user to the browser with Wikipedia Url.
Before each search connectivity is checked, if the user is not connected to the internet, proper message is displayed using Toast.
If the user does not input anything then appropriate message is shown.
Unusual inputs are also handled with proper messages.
Progress bar is shown when the API is triggered.




Issue:

Images retrieved by this API does not load with boath Picasso and Glide. I tried other urls and they worked just fine. I have handled it with success and failure callbacks.



Areas Of Improvement:

Suggestions while the user is typing can be implemented.










           
