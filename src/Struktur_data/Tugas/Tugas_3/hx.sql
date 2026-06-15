CREATE TABLE `administrator` (
  `id_admin` int(3) PRIMARY KEY AUTO_INCREMENT,
  `nama_admin` varchar(20),
  `waktu_jaga` varchar(20)); 

CREATE TABLE `dokter` (
  `id_dokter` int(3) PRIMARY KEY AUTO_INCREMENT,
  `nama_dokter` varchar(20),
  `alamat_dokter` text,
  `no_hp` varchar(20),
  `tanggal_lahir` date,
  `spesialis` varchar(20),
  `waktu_kerja` varchar(20)); 

CREATE TABLE `pasien` (
  `id_pasien` int(3) PRIMARY KEY AUTO_INCREMENT,
  `nama_pasien` varchar(50),
  `alamat_pasien` varchar(50),
  `jenis_kelamin` varchar(50));

CREATE TABLE `daftar` (
  `id_daftar` int(3) PRIMARY KEY AUTO_INCREMENT,
  `id_admin` int(3),
  `id_pasien` int(3),
  FOREIGN KEY (id_admin) References administrator(id_admin) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pasien) References pasien(id_pasien) ON DELETE CASCADE ON UPDATE CASCADE);



CREATE TABLE `dokter_admin` (
  `id_data` int(3) PRIMARY KEY AUTO_INCREMENT,
  `id_admin` int(3),
  `id_dokter` int(3),
   FOREIGN KEY (id_admin) References administrator(id_admin) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_dokter) References dokter(id_dokter) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE `pasien_dokter` (
  `id` int(3) PRIMARY KEY AUTO_INCREMENT,
  `id_dokter` int(3),
  `id_pasien` int(3),
  `waktu_periksa` date,
  `resep` varchar(100),
  FOREIGN KEY (id_dokter) References dokter(id_dokter) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pasien) References pasien(id_pasien) ON DELETE CASCADE ON UPDATE CASCADE);