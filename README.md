# Chill

Chill is a simple movie search demonstration app that uses MVVM + Retrofit + Room that fetches list of movies from iTunes public API.

![chill_1](https://user-images.githubusercontent.com/11737795/187964570-b4f1bf54-6026-460b-ad20-de548364bf8a.gif)![chill_2](https://user-images.githubusercontent.com/11737795/187965290-36921800-dd76-4a62-9c7f-074669d73071.gif)

See the iTunes public API documentation here: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching![chill_2](https://user-images.githubusercontent.com/11737795/187965165-f353f375-9692-4aa1-9f66-2950753628eb.gif)

# Core Features
- MVVM
- Room
- Retrofit
- NetworkBoundResource
- JSON
- Modular Libraries
- Hilt
- Coroutines
- Repository
- Navigation

I used MVVM here because it is much scalable and maintenable. Retain data on configuration changes and it is much easier to test. MVVM architecture pattern prevents coupling of components. MVVM boilerplates your code but it is worth it in the long run.

# Other Libraries Used
- Exoplayer for playing media.
- Glide for setting image.
- Gson converter factory for Retrofit.
- OkHttp for easy view of API response.
