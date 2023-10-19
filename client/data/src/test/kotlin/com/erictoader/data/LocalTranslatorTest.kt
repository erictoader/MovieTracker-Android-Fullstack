package com.erictoader.data

import com.erictoader.data.translator.LocalTranslationNotFoundException
import com.erictoader.data.translator.LocalTranslator
import com.erictoader.domain.translator.TRANSLATION_CONTINUE_WATCHING
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LocalTranslatorTest {

    private val localTranslator = LocalTranslator()

    @Test
    fun `fetchTranslation returns correct translation for existing keyword`() {
        val translation = localTranslator.fetchTranslation(TRANSLATION_CONTINUE_WATCHING)
        assertEquals(LocalTranslator.LOCAL_TRANSLATION_CONTINUE_WATCHING, translation)
    }

    @Test
    fun `fetchTranslation throws exception for non-existing keyword`() {
        assertFailsWith<LocalTranslationNotFoundException> {
            localTranslator.fetchTranslation("non-existing keyword")
        }
    }
}
