# Design Documents
## Main Objective of App
> The app will help you increase your awareness and master time by setting a period and beeping at regular intervals. Based on the number of times you have missed to stop it you will realise how good or bad your presence of mind is in doing some activity. Which over the time lets you increase you awareness

----

## Main Implements
<pre>
- Has 3 screens for navigation
  - WatchDogsGridList Screen
  - CreateWatchDog Screen
  - RunWatchDog Screen
- Has a database for saving and retrieving watchdogs                                                -3
- Has a recycler view that shows all the data from database                                         -4
- Can
  - create watch dog                                                                                -1
  - run watch dog (even on background)                                                              -1
  - can pause and continue watch dog                                                                -1
  - on finish notify on watchdog and rerun watch dog (even on background)                           -1
  - on user stop save watchdog                                                                      -1
- Notification when pressed should reopen app and continue from their not restart the main activity -2
</pre>
### Secondary Implements
<pre>
- Has a progress bar that moves based on how much time is left                                      -1
- Notification background color is different                                                        -5
- Navigation has animations                                                                         -6
- Has a slider for selected number of minutes needed                                                -1
</pre>

----

## Milestones
1. first version should have ability to create timer then run it repeatedly even in the background with notifications popping(2/11/2021)
2. second version should create WatchDogsFragment Screen and make the navigation working (no recycler view, no db)(3/11/2021)
3. third version should create the db and recycler view and show them. Also there should be feature for saving the watchdog to the database(4/11/2021)
4. fourth and final version has navigation animations(4/11/2021)
