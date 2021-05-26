# HRMS_JavaProject
Yazılım Geliştirici Yetiştirme Kampı (JAVA &amp; REACT) Uygulama Projesi (Backend)

* İş arayanlar ve iş verenler için gerekli register işlemleri AuthManager içerisinde ayrı metotlarla gerçekleştiriliyor
* Gerekli alanların doluluk ve uygunluk kontrollerini, Business katmanına yerleştirdiğim kendi Validation sınıfları ile yapılıyor. Tüm alan hataları toplu olarak kullanıcıya geri iletiliyor.
* Email ve TC kimlik no uygunluk kontrolünü basit regex kalıpları ile yaptım, ama eksik olduğu için istisnalara yol açabiliyor.
* İşveren domain-email uygunluğunu da aynı doğrulama sınıfında regex ile gerçekleştirdim. Regex kalıbı ile email adresindeki domain bölümünü ayırıp, website stringi ile eşitliğini kontrol ettim.
* Yine mernis doğrulaması ve email ile aktivasyon kodu gönderimini constructor injection yoluyla eklediğim servisler aracılığı ile yaptım.
* Kayıt verilerinin doğruluk kontrollerinin yapılacağı yere tam olarak karar veremedim ama ana işlemleri yapacak metotları manager sınıflarının içerisinden çağırma yöntemiyle AuthManager içerisinde yapmayı tercih ettim.
* Aktivasyon kısıtlamalarını kontrol edebilmek için login metotları ekledim. Ancak her bir kullanıcı grubunun kontrol şartları farklı olduğu için ayrı login metotları tanımladım kullanıcı grupları için. Bu durum beni rahatsız ediyor ama şimdilik başka bir çözüm bulamadım. Üzerinde çalışıyorum hala.
* Özellikle daha önceden kayıtlı kullanıcıları, belli şartlara bağlı olarak hızlıca ve az kodla tespit edebilmek için Engin hocanın paylaştığı https://www.baeldung.com/spring-data-derived-queries adresindeki yazıdan yararlanarak sorgular hazırladım. Login kontrolü için de aynı anda mail ve password eşleşmesini kontrol eden genişletilmiş sorgu kullandım.
* Activasyon için AuthController içerisinde email ve activation code bilgilerini alan comfirmActivation metodu tanımlayıp, AuthManager içerisindeki ilgili metoda yönlendirme yaptım.

## ERDiagram
![alt ERDiagram](https://github.com/barisertugrul/HRMS_JavaProject/blob/master/resources/hrms_ERDiagram.png?raw=true)

<a href="https://github.com/barisertugrul/HRMS_JavaProject">
  <img align="center" src="https://github-readme-stats.vercel.app/api/pin/?username=barisertugrul&show_owner=true&custom_title=Odevler&theme=vision-friendly-dark&repo=HRMS_JavaProject" />
</a>