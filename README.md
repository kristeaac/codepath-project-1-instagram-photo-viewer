# Project 1 - Instagram Photo Viewer

Instagram Photo Viewer is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: 5 hours spent in total

## Related Work
This project is the second in a series for [CodePath's Android Mobile Bootcamp for Engineers](http://codepath.com/androidbootcamp)

1. **[SimpleTodo](https://github.com/kristeaac/codepath-project-0-todo)** - allows building a todo list with basic todo items management functionality including adding new items, editing and deleting an existing item
2. **Instagram Photo Viewer** - read-only media viewer for Instagram which allows a user to check out popular photos and videos 
3. **[Google Image Search](https://github.com/kristeaac/codepath-project-2-google-image-search)** - a Google Image Search app which allows a user to select search filters and paginate results infinitely
4. **[Simple Tweets](https://github.com/kristeaac/codepath-project-3-twitter-client)** - a simple Twitter client that supports viewing a Twitter timeline and composing a new tweet
5. **[Extended Simple Tweets](https://github.com/kristeaac/codepath-project-4-extended-twitter-client)** - an extension of the previous Twitter client to use a tabbed interface and fragments


## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current popular photos** from Instagram
* [X] For each photo displayed, user can see the following details:
  * [X] Graphic, Caption, Username
  * [X] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [X] User can **pull-to-refresh** popular stream to get the latest popular photos
* [X] Show latest comments for each photo
* [ ] Display each photo with the same style and proportions as the real Instagram
* [X] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [X] Display a nice default placeholder graphic for each image during loading
* [X] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [X] Show last 2 comments for each photo
* [X] Allow user to view all comments for an image within a separate activity or dialog fragment
* [X] Allow video posts to be played in full-screen using the VideoView

The following **additional** features are implemented:

* [X] User can **pull-to-refresh** comments stream

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Jackson](https://github.com/FasterXML/jackson) - JSON parser
- [PrettyTime](https://github.com/ocpsoft/prettytime/) - Social style date and time formatting
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView) - An ImageView that supports rounded corners

## License

    Copyright 2015 Kristy Caster

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.