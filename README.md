# Integração Vonage

### Integração utilizando o desenvolvimento nativo em Java (Android) e Swift (iOS)

Os projetos estão divididos entre android e ios. Para executar o exemplo basta abrir o projeto no Android Studio ou no Xcode.

## Permissões

### iOS

Adicionar em _[Info.plist](iOS/SimpleSFSafariViewController/Info.plist)_ as seguintes permissões:

-   Câmera (NSCameraUsageDescription)
-   Microfone (NSMicrophoneUsageDescription)

```xml
<plist  version="1.0">
	<dict>
		...
		<key>NSCameraUsageDescription</key>
		<string>Usar a câmera</string>
		<key>NSMicrophoneUsageDescription</key>
		<string>Usar o microfone</string>
		...
	</dict>
</plist>
```

### Android

Adicionar em _[AndroidManifest.xml](android/app/src/main/AndroidManifest.xml)_ as seguintes permissões:

```xml
<manifest>
	...
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.RECORD_AUDIO" />
	<uses-permission android:name="android.permission.CAMERA" />
	<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
	...
	<application>
	...
	</application>
</manifest>
```

## Utilizando o Webview

### iOS

Alterar o arquivo _[ViewController.swift](iOS/SimpleSFSafariViewController/ViewController.swift)_

```swift
override func viewDidAppear(_ animated: Bool) {
	let urlString = "https://hml-vonage.conexasaude.com.br/ionicRoom?name=Pac._GUILHERME_MIRANDA&sessionId=45da65d4a6s5d4a6sd54atoken=T1==askdçlkasçldkasçdlaksdçaaoiurweqoriewrewsdfsdf654sdf65sd4f6sd&doctor=false&docpass=false&external=true&plataforma=CONEXA&apikey=99999999"
	if let url = URL(string: urlString) {
		let vc = SFSafariViewController(url: url)
		vc.delegate = self
		present(vc, animated: true)
	}
}
```

> A url é composta pela _api_key, session_id, token, nome do paciente, flag doctor, flag docpass, flag external e flag plataforma_.
> A flag external deve ser _true_.
> A flag doctor deve ser _false_.
> A url deve ser ionic _(ionicRoom ou ionicWaitingRoom)_.

### Android

Alterar o arquivo _[MainActivity.java](android/app/src/main/java/com/example/myapplication/MainActivity.java)_

```java
public  class  MainActivity  extends  AppCompatActivity  {
	...
	private  void  renderWebview(){
		...
		myWebView.loadUrl("https://hml-vonage.conexasaude.com.br/ionicRoom?name=Pac._GUILHERME_MIRANDA&sessionId=45da65d4a6s5d4a6sd54atoken=T1==askdçlkasçldkasçdlaksdçaaoiurweqoriewrewsdfsdf654sdf65sd4f6sd&doctor=false&docpass=false&external=true&plataforma=CONEXA&apikey=99999999");
		...
	}
	...
}
```

> A url é composta pela _api_key, session_id, token, nome do paciente, flag doctor, flag docpass, flag external e flag plataforma_.
> A flag external deve ser _true_.
> A flag doctor deve ser _false_.
> A url deve ser ionic _(ionicRoom ou ionicWaitingRoom)_.

## Gerando a URL para atendimento

https://hml-api.conexasaude.com.br/integration/enterprise/appointment/last/call/{patientId}

```java
curl "https://api.conexasaude.com.br/integration/enterprise/appointment/last/call/{patientId}" \
-H "token: SEU_API_TOKEN"
```

```javascript
// Retorno da API
{
  "status": "200",
  "msg": "Sucesso",
  "object": {
    "appointmentId": 86482,
    "doctorId": 2001,
    "zoomMeetingId": "8898989898098918198",
    "zoomMeetingPasswd": "40404505405405050545",
    "callId": 70854830,
    "zoomMeetingUrl": "https://hml-vonage.conexasaude.com.br/ionicRoom?....",
    "zoomMeetingUrlPatient": "https://hml-vonage.conexasaude.com.br/ionicRoom?...."
  },
  "timestamp": 1591458128186
}
```
