# Chill

Chill is a simple demonstration app that uses MVVM + Retrofit + Room to fetch list of movies from iTunes public API, and uses the data to make a simple movie search app. 

See the iTunes public API documentation here: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching

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
