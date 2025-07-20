package org.girsang.pos.service

import org.girsang.pos.model.Jabatan
import org.girsang.pos.repository.JabatanRepository
import org.springframework.stereotype.Service

@Service
open class JabatanService (private val jabatanRepository: JabatanRepository) {
    fun findAll(): List<Jabatan> = jabatanRepository.findAll()
    fun findById(id: Long) = jabatanRepository.findById(id)
    fun save(jabatan: Jabatan) : Jabatan = jabatanRepository.save(jabatan)
    fun update(id: Long, jabatan: Jabatan) : Jabatan = jabatanRepository.save(jabatan)
    fun delete(id: Long) = jabatanRepository.deleteById(id)
}