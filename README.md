# YouTube
유튜브 앱
- ExoPlayer
- MotionLayout
- Touch

## [ExoPlayer](https://developer.android.com/guide/topics/media/exoplayer?hl=ko)
  - [CodeLabs](https://developer.android.com/codelabs/exoplayer-intro?hl=ko#0)
  - Android의 하위 수준 미디어 API를 토대로 개발된 앱 수준의 미디어 플레이어
  - Android 프레임워크는 아니지만, Google에서 별도로 배포하는 오픈소스 프로젝트
  - ExoPlayer는 맞춤설정이나 확장성이 매우 높아 다양한 사례를 지원하고, YouTube 및 Google Play Movie / TV 등의 Google 앱에서 사용되고 있음

## [Touch](https://developer.android.com/training/gestures/viewgroup?hl=ko)
  - Android에서 Touch 이벤트가 처리되는 과정
  - Activity -> ViewGroup(root~) -> View 순서로 터치가 전달됨
  - View -> ViewGroup(root~) -> Activity 순서로 터치가 처리됨
  - onInterceptTouchEvent()를 true로 return 시 터치가 가로채어지고, 더 아래로 터치가 내려가지 않음
  - onTouch()를 true로 return 시 터치 이벤트가 처리되고, 더 위로 터치 이벤트가 흘러가지 않음

## [MotionEvent 객체](https://developer.android.com/reference/android/view/MotionEvent)
  - 기본적인 구조: ACTION_DOWN -> ACTION_MOVE -> ACTION_UP
  - 멀티 터치 시: ACTION_POINTER_DOWN, ACTION_POINTER_UP
  - 제스처 취소 시: ACTION_CANCLE
  - 터치: 하나의 터치
  - 제스쳐: 여러 개 터치가 모여 해석된 동작, 줌인, 줌아웃, 스크롤 등
