package com.polendina.androidtasknewapp.domain.repository

import com.polendina.androidtasknewapp.R
import com.polendina.androidtasknewapp.data.model.response.NewsEverything
import com.polendina.androidtasknewapp.data.model.response.NewsSources
import com.polendina.androidtasknewapp.domain.model.Publication
import retrofit2.Response

class FakeNewsRepository: NewsRepository {
    val publications = listOf(
        Publication(
            author = "Zac Johnson",
            title = "Michael Keaton Net Worth – How Much is Keaton Worth?",
            description = "Michael Keaton is an American actor with a diverse and successful career in the entertainment industry. Known for his roles in films like “Batman” and “Birdman,” Keaton has managed to accumulate a net worth of $50 million through his work in movies, TV shows,…",
            url = "https://zacjohnson.com/michael-keaton-net-worth-2/",
            urlToImage = R.drawable.michael_keaton_net_worth.toString(),
            publishedAt = "2023-12-03T10:40:06Z",
            content = "Michael Keaton is an American actor with a diverse and successful career in the entertainment industry. Known for his roles in films like “Batman” and “Birdman,” Keaton has managed to accumulate a ne… [+14588 chars]",
            source = Publication.Source(
                id = null,
                name = "Zacjohnson.com"
            )
        ),
        Publication(
            author = null,
            title = "Tesla's Cheapest Cybertruck Will Cost $60,990 And Be Available In 2025",
            description = "After years of development delays and manufacturing snags, Tesla Inc. finally handed over its first Blade Runner-esque Cybertrucks to customers.",
            url = "https://www.ndtv.com/world-news/teslas-cheapest-cybertruck-will-cost-60-990-and-be-available-in-2025-4628866",
            urlToImage = R.drawable.adldug_tesla_cybertruck_december.toString(),
            publishedAt = "2023-12-03T07:41:22Z",
            content = "Tesla shares fell as much as 2.1% before the start of regular trading Friday.\r\nAfter years of development delays and manufacturing snags, Tesla Inc. finally handed over its first Blade Runner-esque C… [+4062 chars]",
            source = Publication.Source(
                id = null,
                name = "NDTV News"
            )
        ),
        Publication(
            author = "Tom Krisher",
            title = "BMW recalls SUVs after Takata air bag inflator blows apart, hurling shrapnel and injuring driver",
            description = "BMW is recalling a small number of SUVs in the U.S. because the driver's air bag inflators can blow apart in a crash, hurling metal shrapnel and possibly injuring or killing people in the vehicles.",
            url = "https://techxplore.com/news/2023-12-bmw-recalls-suvs-takata-air.html",
            urlToImage = R.drawable.bmw_recalls_suvs_after.toString(),
            publishedAt = "2023-12-03T10:43:56Z",
            content = "BMW is recalling a small number of SUVs in the U.S. because the driver's air bag inflators can blow apart in a crash, hurling metal shrapnel and possibly injuring or killing people in the vehicles.\r\n… [+4177 chars]",
            source = Publication.Source(
                id = null,
                name = "Tech Xplore"
            )
        ),
        Publication(
            author = "Christian de Looper",
            title = "Tesla’s EV plug is great, but smoother payment is the fix we really need",
            description = "NACS is becoming the standard connector for charging in the U.S., but while that will make EV charging a lot more convenient, there's more to it than that.",
            url = "https://www.digitaltrends.com/cars/teslas-ev-plug-is-great-but-smoother-payment-is-the-fix-we-really-need/",
            urlToImage = R.drawable.supercharger.toString(),
            publishedAt = "2023-12-03T19:00:18Z",
            content = "Tesla\r\nIt’s finally happening. Up until now, we’ve had a few different connector types on electric vehicles, but everyone is finally moving toward one connector. The NACS connector, invented by Tesla… [+4878 chars]",
            source = Publication.Source(
                id = null,
                name = "Digital Trends"
            )
        ),
        Publication(
            author = "Zac Johnson",
            title = "Brad Pitt Net Worth – How Much is Pitt Worth?",
            description = "Brad Pitt is one of the wealthiest actors in Hollywood, with an estimated net worth of $400 million. His financial success can be attributed to his lucrative career as an actor and film producer, as well as his endorsement deals. Pitt’s consistent high salari…",
            url = "https://zacjohnson.com/brad-pitt-net-worth-2/",
            urlToImage = R.drawable.networth.toString(),
            publishedAt = "2023-12-03T18:45:28Z",
            content = "Brad Pitt is one of the wealthiest actors in Hollywood, with an estimated net worth of $400 million. His financial success can be attributed to his lucrative career as an actor and film producer, as … [+20403 chars]",
            source = Publication.Source(
                id = null,
                name = "Zacjohnson.com"
            )
        ),
        Publication(
            author= "Al Root",
            title = "What Determines the Value of Tesla, Ford, BYD, and Other Car Stocks? Geography.",
            description = "Don't try to figure out why Tesla is valued like it is or why GM shares trade for less than one-quarter of the S&P 500 multiple. You'll lose your mind.",
            url = "https://www.barrons.com/articles/geography-value-tesla-ford-byd-car-stocks-634b550b",
            urlToImage = R.drawable.tier.toString(),
            publishedAt = "2023-12-03T18:33:00Z",
            content = "The stock market is supposed to be efficient, discounting all available information into corporate valuations immediately. Its pretty good at being efficient, but not everything makes sense all the t… [+4486 chars]",
            source = Publication.Source(
                id = null,
                name = "Barron's"
            )
        )
    )
    override suspend fun getNewsEverything(
        apiKey: String,
        searchQuery: String
    ): Response<NewsEverything> {
        return Response.success(
            NewsEverything(
                status = "200",
                totalResults = 100,
                articles = publications
            )
        )
    }
    override suspend fun newsSources(
        apiKey: String,
        category: String,
        country: String,
        language: String
    ): Response<NewsSources> {
        return Response.success(
            NewsSources(
                status = "200",
                sources = emptyList()
            )
        )
    }
    override suspend fun getTopHeadlines(
        apiKey: String,
        searchQuery: String,
        sources: String,
        country: String,
        category: String,
        pageSize: Int
    ): Response<NewsEverything> {
        return Response.success(
            NewsEverything(
            status = "200",
            totalResults = 100,
            articles = publications
        ))
    }
}