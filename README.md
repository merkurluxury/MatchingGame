
# MatchingGame Android Mobil Uygulaması

_ * Bu uygulama Android Studio üzerine inşa edilmiştir, Java dili ile kodlanmıştır.
_ * Uygulama, Android Studio'nun yerleşik emülatörü (Pixel 3 API 30) kullanılarak test edildi.Fakat bu emğlatör bazı durumlarda çalışmıyor
Apk çıkarıp telefona yüklendiğinde uygulama problemsiz çalışıyor.
_ url'den görüntü almak ve ımagebutton'a uygulamak için üçüncü traf bir kitaplık kullanıldı.

## Uygulama Hakkında

Matchingame, kullanıcının hafıza eşleştirme oyununun iki versiyonunu oynayabileceği basit bir android mobil uygulamadır.
Kullanıcılar, belirli bir tahtadaki tüm eşleşmeleri bulana kadar çevirebilirler.
Tüm eşleşmeler tamamlandığında, kullanıcılara tekrar oynamak isteyip istemedikleri sorulur.


## Arayüz Hakkında.

Uygulama başlatıldıktan sonra, karşımıza ana arayüz çıkacaktır.



Kullanıcıların seçebilecekleri üç sürümü vardır:
1. "Oyunu Başlat 4x4": 4x4 ızgara - 2 görüntüden oluşan 8 set eşleştir
2. "Oyunu Başlat 6x6": 6x6 ızgara - 2 görüntüden oluşan 18 set eşleştir


Kullanıcılar 2 seçenekten birine tıkladığında, kartlı bir tahta görünecektir:


En sol üstte bir süre sayacı bulunmaktadır. Bu sayaç oyun tamamlanana kadar geçen süreyi hesaplamak için kullanılmaktadır.
En sağ üstte puan durumunu gösteren bir sayaç bulunmaktadır.
Her doğru eşleşme +10, her yanlış eşleşme -2 puan olacak şekilde ekranda oyunun puanı gösterilebilir.
Süre sayacının hemen altında hamle sayımızı hesaplayan bir çevirme sayacı bulunmaktadır. Her karta bastığımızda bu sayaç bir artmaktadır.
Eşleşme sayacı ise bize kaç adet çift bulduğumuzun sayısını vermektedir.



Yeşil dikdörtgenler, kullanıcıların çevirebileceği kartları temsil etmektedir.
Oyunu oynayan kişiler eşleşmeleri bulmak için dikdörtgenlere tıklamalıdırlar.
Tüm eşleşmeler tamamlandığında kullanıcıya tekrar oynamak isteyip istemediği sorulur. 
Evet butonu tıklandığında kartlar karıştırılır ve yeni deste gelir.

Oyuncunun skoru ve oynama süresi sqlite'ın kardeşi sharedpreferences kullanılarak saklanmaktadır.


## Uygulama Hakkında

Uygulama üç etkinlikten oluşur; bir mainActivity etkinlik ve iki oyun Activity.


_activity_main _: sırasıyla hafıza oyunlarının farklı sürümlerini başlatan 2 buton ve bu sürümlerin en son skorlarını tutan ekran içerir.

activity_4×4 ve activity_6×6: Resim url'lerini alır. Alınacak resim sayısı NUM_IMGS değişkeni tarafından belirtilir. Düzendeki her düğme bir Kart nesnesiyle ilişkilendirilir. Çevrilen kartlar geçici olarak bir değişkende saklanır, böylece tüm kartlar çevrildiğinde kontrol edilebilirler.
Bir eşleşme bulunduğunda, eşleşen kartlar belirli bir veri yapısında saklanır. Kümedeki eşleşen kartlar tekrar çevrilemez ve kümenin boyutu kullanılabilir kart sayısına ulaştığında oyun sona erer.

Uygulama Gifleri: https://drive.google.com/drive/folders/1g7b0QOQQ22Fy2d-OiOpixbps6ZMTAzC_?usp=sharing
