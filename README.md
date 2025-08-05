# 🐶 DOG DICTIONARY

An Android application that serves as an encyclopedia for dog breeds, providing detailed information, interactive features, and educational content for dog lovers.

## 📱 Overview

**DOG DICTIONARY** is a mobile app that lets users:
- Search for various dog breeds using different attributes (breed, weight, height, name)
- Watch educational YouTube videos about dogs
- Take quizzes related to the video content
- Bookmark favorite breeds into a personal list

The app is designed with simplicity and user-friendliness in mind, with an intuitive UI using a warm color scheme (orange, banana cream, and brown).

## 🔧 Features & Activities

### 1. `MainActivity`
- KakaoTalk login/logout
- Redirects to the main app upon login
- Displays app title

### 2. `IntroduceActivity`
- Shows app introduction and user name
- Navigation buttons to:
  - YouTube activity
  - Breed search activity
- Interactive navigation drawer

### 3. `BreedActivity`
- SearchView for finding breeds
- Toggle search type (e.g., by name, weight)
- ListView to display breed information
- Navigation to personal list

### 4. `MyActivity`
- Shows user's bookmarked breeds in a list

### 5. `YouTubeActivity`
- Embeds YouTube video related to dog knowledge
- Button to proceed to quiz

### 6. `QuizActivity`
- 5 True/False questions based on the video
- Checkbox inputs and answer-check button

**All activities (except MainActivity and YouTubeActivity) are accessible via the navigation menu.**

## 🌐 API Integration

- [`TheDogAPI`](https://api.thedogapi.com): Fetch breed data
- [`Kakao Developers`](https://developers.kakao.com): Implement social login with KakaoTalk

## ⚙️ Implementation Challenges

- **Kakao Login**: Implementing callbacks and hash key setup
- **Local Storage**: Built a custom database to store user bookmarks persistently
  - Used Kotlin coroutines to handle access speed limitations
  - Created separate databases for different logged-in users
- **Live Search**: Implemented `onQueryTextChange` to update list in real time
- **Data Filtering**: Supported 4 types of search filters
- **UI Elements**:
  - Emoji animation on launch
  - Embedded YouTube video player
  - Navigation drawer
  - Image URL conversion for breed thumbnails

## 🎨 UI/UX Design

- Simple, clean layout
- Warm color palette: orange, banana cream, brown
- Designed for ease of use by first-time users
- Visual clarity to help users understand app purpose

## 💡 Future Improvements

- Add user account customization
- More interactive quiz types
- Expand video library and breed data

---

Feel free to contribute or report issues!
