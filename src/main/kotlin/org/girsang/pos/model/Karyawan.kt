package org.girsang.pos.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Karyawan (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var noKaryawan: String ="",
    var namaLengkap: String = "",
    var tempatLahir: String = "",
    var tanggalLahir: LocalDate = LocalDate.now(),
    var jenisKelamin: String = "",
    var tanggalMasuk: LocalDate = LocalDate.now(),

    var jabatan: String = "",
    var namaPanggilan: String = "",
    var password: String = ""
)
