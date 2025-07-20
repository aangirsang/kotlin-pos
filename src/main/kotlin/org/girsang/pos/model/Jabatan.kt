package org.girsang.pos.model

import jakarta.persistence.*


@Entity
data class Jabatan (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var jabatan: String =""

)