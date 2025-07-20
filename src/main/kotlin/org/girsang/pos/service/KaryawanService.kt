package org.girsang.pos.service

import org.girsang.pos.model.Karyawan
import org.girsang.pos.repository.KaryawanRepository
import org.springframework.stereotype.Service

@Service
open class KaryawanService(private val karyawanRepository: KaryawanRepository) {
    fun findAll(): List<Karyawan> = karyawanRepository.findAll()
    fun findById(id: Long) = karyawanRepository.findById(id)
    fun save(karyawan: Karyawan) : Karyawan = karyawanRepository.save(karyawan)
    fun update(id: Long, karyawan: Karyawan) : Karyawan = karyawanRepository.save(karyawan)
    fun delete(id: Long) = karyawanRepository.deleteById(id)
}