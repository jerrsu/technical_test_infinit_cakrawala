# Technical Test

## Spesifikasi
- Java 17
- Postgresql
- Maven 3.6 or later

## Requirement
1. Buat database menggunakan postgres dengan nama database 'technical_test'.
2. Ubah credential datasource di application.yml sesuai username,password dan url yang anda punya.

## Menjalankan Program
1. Build project dan memastikan semua sudah ok dan tersambung baik dengan database
```sh
 mvn clean package
```
2. Jalankan project (dengan terminal)
```sh
 mvn spring-bbot:run
```

## Uji Coba

* Jika sudah berhasil menjalankan projectnya, migrasi credential untuk login sudah otomatis terupdate.
* Import file **_Tech Test Infinit Cakrawala.postman_collection.json_** ke postman.
* Cek endpoint Healt Check untuk mengetahui apakah service sudah up atau belum.
* Login terlebih dahulu agar mendapatkan akses token untuk akses ke semua endpoint.
* Credential sudah terpasang dibody endpoint Login.
* Setelah itu anda bisa melakukan CRUD data employee dengan menempelkan akses token yang telah dibuat saat login diatas.
