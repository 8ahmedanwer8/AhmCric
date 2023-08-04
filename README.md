# AhmCric
#### Playstore: https://play.google.com/store/apps/details?id=com.confuseddevs.ahmcricfinale&hl=en&gl=US
#### Demo video: https://www.youtube.com/watch?v=SCXKYBLiJB4&t=110s

AhmCric android app uses RoomDB and Firebase to allow you to track your cricket players' win/loss record and share them with friends. 
<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <img src="https://play-lh.googleusercontent.com/ohEeMRO35xfc434ZJQ6LDZfYRnAmsJwxP6cheqJC65dDJZ04a1ktGMcuu1yiK7PJ1gm5=w480-h960" width = 250>
</div>
<div align="center">
  <a href="https://github.com/8ahmedanwer8/repo_name">
    <img src="https://github.com/8ahmedanwer8/AhmCric/assets/84689304/33c497c3-a2dd-481e-8f93-99d017426d0d" alt="Logo">
  </a>
<h3 align="center">AhmCric</h3>

  <p align="center">
    Cricket Score Management Mobile App
    <br />
    <a href="https://play.google.com/store/apps/details?id=com.confuseddevs.ahmcricfinale&hl=en&gl=US">Download on Playstore</a>
    <br />
    <a href="https://www.youtube.com/watch?v=SCXKYBLiJB4&t=110s">View Video Demo</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project
I made AhmCric to solve the problem of failing to remember wins and losses every time I played cricket with friends during the lockdowns (our gatherings were within the max capacity). AhmCric is a multi-screen (uses fragments) android app that lets me locally store the names of my friends and their wins, losses, draws and calculate win/loss ratio. It uses RoomDB which is an abstraction of SQlite. Additionally, the user can randomly generate two cricket teams (another problem we struggled with) and share/exchange their roster and stats with any users around the world using Firebase’s Real-time Database. 


### Features

* I implemented a database with RoomDB, SQlite and Kotlin coroutines to make asynchronous and efficient queries on the background thread
* I leveeraged the MVVM design pattern by having a ViewModel component that acted as the intermediary between the UI and the business logic
* I created a comprehensive UI/UX making use of RecyclerViews, CardViews, TextViews, Alert dialogs, Toasts, Buttons and Fragments for the layout
* I enabled user-to-user sharing of data by integrating the app with Firebase Real-time Database
* I published the app on Google Play

<!-- RUNNING LOCALLY -->
## Running Locally

1. Clone the repo
   ```sh
   git clone https://github.com/8ahmedanwer8/ceiconnect.git
   ```
2. Install packages
   ```sh
   yarn install
   ```
3. Run app
   ```js
   yarn dev
   ```

<!-- CONTACT -->
## Contact

[Twitter](https://twitter.com/https://twitter.com/AhmedAn17381286) 
<br />
[LinkedIn](https://www.linkedin.com/in/8ahmed8/)
****

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/8ahmedanwer8/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/8ahmedanwer8/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/8ahmedanwer8/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/8ahmedanwer8/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/8ahmedanwer8/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/8ahmedanwer8/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/8ahmedanwer8/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/8ahmedanwer8/repo_name/issues
[license-shield]: https://img.shields.io/github/license/8ahmedanwer8/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/8ahmedanwer8/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
