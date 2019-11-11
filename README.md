#Introduction:

Please imagine that a very significant client wants to become our partner.
He is a huge fan of Rick and Morty, and he tests different companies with a simple PoC app that
will be a seed for the future project.
When he came, You were on holiday. There were no free developers, so the Project Manager
decided to start writing this app by himself with the assistance of a Content Specialist
and the Android trainee.
The scope of the app is not very vast:

- Splash screen with Rick and Morty logo

- List of episodes

- List of characters

- List of locations

You can find more about api on: https://rickandmortyapi.com

What they managed to implement is:
- Splash and Episodes activities
- Basic dagger config for the networking, retrofit config, etc

Now, Your role:
You return from your vacation, you see this mess, and you are asked to finish it. You are a solid Android Guy, and I’m sure you will save the universe... or at least this project.


#tasks:
Tasks are ordered by their priority - do as many as You want.

- Fix splash screen - Splash screen is ready (let’s not bother with a resolution of the graphic).
The only problem with it is that user doesn’t see it. Please make it visible for 2 seconds,
then open the Episodes screen.

- As you know, not very experienced guys started this project, 
so they downloaded some adapter from the web (StackOverflow Driven Development), but something went wrong. There is only a few episodes, please fix that app fetch more episodes when user scroll down the list.

- Add Bottom Navigation so the user can choose what he wants to see. Bottom Navigation should allow displaying Episodes, Characters, Locations screens.
 
- There is no error handling when performing the API call. Please, handle potential errors and present snackbar with the error message to the user.

- There is no loader when a user opens any screen - please add it, so a user can see something before the data is downloaded.

- There is no loader when a user scrolls down. Add new view type to the adapter, so the progress bar is visible when another page is being downloaded.

- Handle the state. We don’t want all the data to be gone when user rotates the screen.

- Refactor - Show off. Trainee didn’t know MVP or any other design patterns. It’s also a beginning of the potentially huge project. Refactor it so that you can write some unit tests. You can move classes to separate files, change the packaging, use any library you want, extract constants from
 XML. Just show how you would do it.

- Tests - Please write unit tests to the MainActivity logic (no need for 100% coverage), just basic scenarios -> presenting list and error handling.

- UI Tests - Please write at least one UI test for the MainActivity.

##Good luck ; )