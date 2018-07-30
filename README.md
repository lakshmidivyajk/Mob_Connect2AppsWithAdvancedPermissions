# Mob_Connect2AppsWithAdvancedPermissions

This project is to show how to two mobile apps can be connected, with Advanced permissions. A sample to show my skills on fragments, android perrmissions, broadcast recievers. Heres the short summar of two apps:

1. Project3A1(shortly refered as A1) consists of a single activity containing two fragments. The first fragment consists of a
list naming at least 6 Chicago landmarks (e.g., the Museum of Science and Industry, Wrigley Field, the
Lincoln Park Zoo, etc.) Interactive app users can select one of the landmarks. When this happens, the
selected item stays highlighted and the second fragment displays the web page of the highlighted item.
This app also defines an options menu with at least two items: (1) exiting A1 and (2) launching the next
application, A2. A1 launches A2 by sending a system broadcast. In addition, application A1 maintains
an action bar. The action bar shows the name of the application (Project3A1) and the overflow area.


2.p3gallery(shortly refered as A2) is a picture repository; pictures are displayed in an Android Gallery. The gallery shows
images from each of the landmarks discussed in application A1. There is at least one picture
from each landmark contained in A1. A1 first starts the gallery in A2 by sending a global broadcast;
A2 contains a broadcast receiver with appropriate filters to catch A1’s broadcast. When it receives a
broadcast, A2 launches the gallery even though A2 was not running before A1 sent the broadcast. In
addition, A2 defines a new, dangerous-level permission. The broadcast receiver contained in A2 requires
that the broadcast sender (e.g., application A1) own this permission in order to respond to a broadcast.



Implementation

All that you need is Android Studio IDE.
Download the file and open it in the IDE, click run button to see the sample of the application.
