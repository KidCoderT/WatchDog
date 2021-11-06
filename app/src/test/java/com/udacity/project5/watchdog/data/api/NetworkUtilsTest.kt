package com.udacity.project5.watchdog.data.api

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class NetworkUtilsTest {

    @Test
    fun givenJsonToParse_whenParsedWithFunction_thanParsedDataIsArrayList() {
        // Given
        val jsonToParse =
            "{\"count\":20,\"totalCount\":38,\"page\":1,\"totalPages\":2,\"lastItemIndex\":20,\"results\":[{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"2xpHvSOQMD\",\"author\":\"Helmut Schmidt\",\"content\":\"The biggest room in the world is room for improvement.\",\"authorSlug\":\"helmut-schmidt\",\"length\":54,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"HMBEfGB94i\",\"author\":\"Henry Ford\",\"content\":\"Whether you think you can or you think you can't, you are right.\",\"authorSlug\":\"henry-ford\",\"length\":64,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"lJ60rOYWci\",\"author\":\"Neil Gaiman\",\"content\":\"The one thing that you have that nobody else has is you. Your voice, your mind, your story, your vision. So write and draw and build and play and dance and live as only you can.\",\"authorSlug\":\"neil-gaiman\",\"length\":177,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"XNLGqepInX\",\"author\":\"Amy Poehler\",\"content\":\"There’s power in looking silly and not caring that you do.\",\"authorSlug\":\"amy-poehler\",\"length\":58,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"aEPNVog6sT\",\"author\":\"Eric Hoffer\",\"content\":\"In times of change, learners inherit the earth, while the learned find themselves beautifully equipped to deal with a world that no longer exists.\",\"authorSlug\":\"eric-hoffer\",\"length\":146,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"QdK00IhCNX\",\"author\":\"Larry Page\",\"content\":\"If you're changing the world, you're working on important things. You're excited to get up in the morning.\",\"authorSlug\":\"larry-page\",\"length\":106,\"dateAdded\":\"2021-06-18\",\"dateModified\":\"2021-06-18\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"jeB48pbKLwjx\",\"content\":\"In the middle of every difficulty lies opportunity.\",\"author\":\"Albert Einstein\",\"authorSlug\":\"albert-einstein\",\"length\":51,\"dateAdded\":\"2021-05-07\",\"dateModified\":\"2021-05-07\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"8Lkh3gqcSh\",\"content\":\"Difficulties increase the nearer we get to the goal.\",\"author\":\"Johann Wolfgang von Goethe\",\"authorSlug\":\"johann-wolfgang-von-goethe\",\"length\":52,\"dateAdded\":\"2021-01-19\",\"dateModified\":\"2021-01-19\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"ML3d7eMwRfr3\",\"content\":\"There is nothing impossible to him who will try.\",\"author\":\"Alexander the Great\",\"authorSlug\":\"alexander-the-great\",\"length\":48,\"dateAdded\":\"2020-12-17\",\"dateModified\":\"2020-12-17\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"c1krnKmkNc\",\"author\":\"Abraham Lincoln\",\"content\":\"I'll prepare and someday my chance will come.\",\"authorSlug\":\"abraham-lincoln\",\"length\":45,\"dateAdded\":\"2020-12-17\",\"dateModified\":\"2021-06-17\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"t6j0hQKmXbM9\",\"content\":\"The dream was always running ahead of me. To catch up, to live for a moment in unison with it, that was the miracle.\",\"author\":\"Anaïs Nin\",\"authorSlug\":\"anais-nin\",\"length\":116,\"dateAdded\":\"2020-12-10\",\"dateModified\":\"2020-12-10\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"XHblJb9aZ68R\",\"content\":\"A person who never made a mistake never tried anything new.\",\"author\":\"Albert Einstein\",\"authorSlug\":\"albert-einstein\",\"length\":59,\"dateAdded\":\"2020-12-10\",\"dateModified\":\"2020-12-10\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"ncgMsMKJiBiN\",\"content\":\"If you aren't going all the way, why go at all?\",\"author\":\"Joe Namath\",\"authorSlug\":\"joe-namath\",\"length\":47,\"dateAdded\":\"2020-11-13\",\"dateModified\":\"2020-11-13\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"yCY2q20UK4Uf\",\"content\":\"Let us sacrifice our today so that our children can have a better tomorrow.\",\"author\":\"A. P. J. Abdul Kalam\",\"authorSlug\":\"a-p-j-abdul-kalam\",\"length\":75,\"dateAdded\":\"2020-11-10\",\"dateModified\":\"2020-11-10\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"NNRKhkipf13G\",\"content\":\"Keep your eyes on the stars and your feet on the ground.\",\"author\":\"Theodore Roosevelt\",\"authorSlug\":\"theodore-roosevelt\",\"length\":56,\"dateAdded\":\"2020-10-29\",\"dateModified\":\"2020-10-29\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"279KuN7AAeyu\",\"content\":\"Life is 10% what happens to you and 90% how you react to it.\",\"author\":\"Chuck Swindoll\",\"authorSlug\":\"chuck-swindoll\",\"length\":60,\"dateAdded\":\"2020-08-27\",\"dateModified\":\"2020-08-27\"},{\"tags\":[\"famous-quotes\",\"inspirational\",\"power-quotes\"],\"_id\":\"c2crwHSzalPu\",\"content\":\"Difficulties are meant to rouse, not discourage. The human spirit is to grow strong by conflict.\",\"author\":\"William Ellery Channing\",\"authorSlug\":\"william-ellery-channing\",\"length\":96,\"dateAdded\":\"2020-06-24\",\"dateModified\":\"2020-06-24\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"2Mx1de_dko_\",\"content\":\"Mountains cannot be surmounted except by winding paths.\",\"author\":\"Johann Wolfgang von Goethe\",\"authorSlug\":\"johann-wolfgang-von-goethe\",\"length\":55,\"dateAdded\":\"2020-05-21\",\"dateModified\":\"2020-05-21\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"bT4wt0vfm_F\",\"content\":\"If you can dream it, you can do it.\",\"author\":\"Walt Disney\",\"authorSlug\":\"walt-disney\",\"length\":35,\"dateAdded\":\"2020-03-27\",\"dateModified\":\"2020-03-27\"},{\"tags\":[\"famous-quotes\",\"inspirational\"],\"_id\":\"ME7DOkrq0U-m\",\"content\":\"Failure will never overtake me if my determination to succeed is strong enough.\",\"author\":\"Og Mandino\",\"authorSlug\":\"og-mandino\",\"length\":79,\"dateAdded\":\"2020-02-22\",\"dateModified\":\"2020-02-22\"}]}"

        // When
        val parsedData = parseQuotesJsonResult(jsonToParse)

        // Than
        assertThat(parsedData.size, `is`(20))
        assertThat(parsedData::class.simpleName, `is`("ArrayList"))
    }
}