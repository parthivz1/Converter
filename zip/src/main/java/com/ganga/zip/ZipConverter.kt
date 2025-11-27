package com.ganga.zip

import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipConverter {

    fun zipFiles(files: List<File>, outputZip: File) {
        ZipOutputStream(outputZip.outputStream()).use { zipOut ->
            files.forEach { file ->
                zipOut.putNextEntry(ZipEntry(file.name))
                file.inputStream().copyTo(zipOut)
                zipOut.closeEntry()
            }
        }
    }
}