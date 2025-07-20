package org.girsang.pos.repository

import org.girsang.pos.model.Jabatan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JabatanRepository : JpaRepository<Jabatan, Long>