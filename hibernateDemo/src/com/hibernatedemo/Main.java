package com.hibernatedemo;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {

	public static void main(String[] args) {
		
		//Session diye bir olay vardır. Veri tabanına sorgu gönderirsiniz 
		//Sorgu göndermeden bir tane session açılır.
		//Sorgunuzu yazıp session oturum gibi düşünün.
		//Sessionun kendisini yollamış oluyorsunuz.
		//Session mantığı insert ettin update ettin update sıkıntı oldu geri alabilmek için TRANSACTION yönetimi denir. Session mantığı 
		//Session mimarisini sessionFabrikasıyla oluşturulur.
		//Uygulamalarda bir kez oluşturulması gereken kısımdır.
		
		SessionFactory factory = new Configuration() //Sessionu SessionFabrikasıyla oluşturuyoruz
				.configure("hibernate.cfg.xml") //Dosyamızın adı 
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
				//Fabrikamız oluştu
				//Bir konfigrasyon oluşturduk. Konfigrasyon diye bir class oluşuyor
				//factorye atıyoruz.
		
		//Unit of work tasarım deseni:
		//Unit Of Work tasarım deseni, yazılım uygulamamızda veritabanıyla ilgili her bir aksiyonun anlık olarak .
		//veritabanına yansıtılmasını engelleyen ve buna nazaran tüm aksiyonları biriktirip bir bütün olarak bir defada tek bir connection 
		//üzerinden gerçekleştirilmesini sağlayan ve böylece veritabanı maliyetlerini oldukça minimize eden bir tasarım desenidir
		Session session = factory.getCurrentSession();
		
		try { //Korumak için standarttır try  içine aldık.
			session.beginTransaction(); //Transaction başla
			//HQL--> Hibernate Query Language
			
			//CRUD ---> Create Read Update Deletct
			//Select *from city aynı şey aşağıdaki
			//from City c where c.countryCode ='TUR' AND c.district ='Ankara'
			// "from City c where c.name LIKE '%kar%'" içinde kar bulunanlar     kar%(Kar ile başlasın demek)
			//"from City c ORDER BY c.name" şehirleri seç sırala isme göre default ASC-Ascending gelir. DES -->Descending
			
			  List <City> cities = session.createQuery("from City").getResultList();
			  
			  //Şehirler tablosundan çek //getResultList hibernatede kullanılıyor.List
			 // tipinde döndürüyor. //GROUP BY --> BİR ŞEYE GÖRE GRUPLAMA Örneğin CountryCode
			  //TUR sayısı gibi. 
			  for (City city:cities) { 
				  System.out.println(city.getName());
			  }
			 
			
			//INSERT 
			
			/*
			 * City city = new City(); city.setName("Düzce 10"); city.setCountryCode("TUR");
			 * city.setDistrict("Karadeniz"); city.setPopulation(100000);
			 * 
			 * session.save(city); //Bir obje olarak city gönderiyorsunuz
			 * sout("Şehir eklendi")
			 */			
			
			//UPDATE
			
			//Sessiondaki data elinizde olması gerekiyor.
			/*
			 * City city = session.get(City.class,4086); //Cityden hangi ID olarak düşün
			 * ikinci parametre ID System.out.println(city.getName());
			 * city.setPopulation(11000); session.save(city); //Olan bütün işlemleri kaydet
			 * bunu unutma System.out.println("Şehir güncellendi");
			 */
			
			/*
			 * //DELETE
			 * 
			 * City city = session.get(City.class, 4086); //eline data ver
			 * session.delete(city); //Sil bu kadar basit.
			 * System.out.println("Veri silindi... ");
			 */
		
			session.getTransaction().commit(); //Commit et gerçekleştir. Veri tabanına bas
			
			
		}finally {
			factory.close(); //İşimiz bittiğinde fabrikayı kapatmış olacağız.
			
		}
	}

}
