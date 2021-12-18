# Notes
For integration testing, please deploy to the fakeDebug build variant. 

## Architecture
This project uses my typical architectural pattern on Android which looks something like MVP + VM.
The reason for it is that MVP suffers from a problem of where to store the model, and MVVM has the
opposite problem of where to place the presentation logic. 

The idea with this architecture is that the VM once again does what it is intended; it's just a
model of what the View needs. I pull the presentation logic back into a presenter which is made
very easy to test.

## Libraries
My general goal with libraries is to prefer to use either a platform or standard library option
unless it makes sense not to. For example, handwriting my own DI code here is far simple, easier, 
and resistent to change than using Dagger. The key question I ask is: Does this library
solve more problems than it creates? 

## Testing
As mentioned in the architecture section, my main goal is to pull all or most of the presentation
logic into a class which can be easily tested on the JVM. By applying passive view/humble object,
my Views become much simpler to write. I don't typically use espresso and prefer to test the ui
via integration tests with fake data, but I am absolutely willing to adjust to meet a team/client's
requirements.

For integration tests, I chose to set up a separate source set to return fake data. My normal 
process is to do the bulk of the testing on my physical android device, and then test with a variety
of emulators with different OS versions and form factors.