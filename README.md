<h1 align="left">Приложение-концепт для онлайн магазина(в разработке)</h1>

![progress](https://img.shields.io/badge/Progress-In%20work-yellow)

Тестовое задание для компании Effective Mobile

[Figma](https://www.figma.com/file/uBgR9MCzE8e4dca2tzuB8b/test2?node-id=0%3A1&t=yBiPYIySqOorkBCY-0)

<h3 align="left">Полное превью</h3>



https://user-images.githubusercontent.com/52128742/205938432-96ac2c84-df89-4df6-9877-5cbde47c5303.mp4



<h1 align="left">Стэк </h1>

- MVVM Clean Architecture project
- Kotlin Coroutines
- Jetpack Compose
- Kotlin Flow
- Hilt
- Room
- Retrofit

<h2 align="left">Принцип: </h2>

I

Пользователь заходит в приложение и видит список актуальных товаров выбраной категории


II

Пользователь может перейти к товару по нажатию по нему, добавить товар в избранное, а также просканировать QR-код товара

III

Пользователь открывает страницу товара, может просмотреть его фотографии, выбрать интресующие характеристики и добавить товар в корзину

IIII

Пользователь открывает корзину, где может редактировать её наполнение и перейти к оформлению


<h2 align="left">Функционал: </h2>

1) Проделана большая работа в контексте разработки приложения с использованием Android Compose, построения многомодульной архитектуры приложения
- Основные компоненты лежат в модуле [core](https://github.com/KirsonBrz/EcommerceConcept/tree/master/core) 
- Реализован функциональный модуль [main](https://github.com/KirsonBrz/EcommerceConcept/tree/master/main), где происходит поиск и добавление товара в корзину

2) Отдельное внимание уделяется анимациям, занимающим важную роль в восприятии интерфейса пользователем, например:
- [скрытие нижней навигации](https://github.com/KirsonBrz/EcommerceConcept/blob/master/core/ui/uikit/src/main/java/com/kirson/ecommerceconcept/components/BottomNavigationBar.kt)

![gifBottom](https://media.giphy.com/media/eMIn9D2kMMofkSNaf9/giphy.gif)

- [сканер QR-кода на странице поиска](https://github.com/KirsonBrz/EcommerceConcept/blob/master/main/ui/src/main/java/com/kirson/ecommerceconcept/components/GlobalSearchComponent.kt)

![gifQR](https://media.giphy.com/media/8494H3ut6FSDT7yKSu/giphy.gif)

- [состояние сети](https://github.com/KirsonBrz/EcommerceConcept/blob/master/core/ui/uikit/src/main/java/com/kirson/ecommerceconcept/components/ConnectivityStatus.kt)

![gifNet](https://media.giphy.com/media/iyNFm4utfVpVPC6U3E/giphy.gif)

3) Навигация в приложении реализована благодаря модулю [navigation](https://github.com/KirsonBrz/EcommerceConcept/tree/master/navigation), под капотом: NavigationComponent и NavGraph от Compose

4) Для иньекции зависимостей используется Hilt, но для правильной реализации модулей и чистой архитектуры необходим Dagger2 (например, интерфейс репозитория в domain слое, где мы пользуемся его методами, но не знаем их реализации)

