package org.girsang.pos.repository

import org.girsang.pos.model.Karyawan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface KaryawanRepository : JpaRepository<Karyawan, Long>