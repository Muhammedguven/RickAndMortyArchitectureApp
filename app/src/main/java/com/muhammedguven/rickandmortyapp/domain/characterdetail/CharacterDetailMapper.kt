package com.muhammedguven.rickandmortyapp.domain.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.model.LocationResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.OriginResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import com.muhammedguven.rickandmortyapp.domain.model.Location
import com.muhammedguven.rickandmortyapp.domain.model.Origin
import com.muhammedguven.rickandmortyapp.domain.model.Results
import javax.inject.Inject

class CharacterDetailMapper @Inject constructor() {

    fun mapFromResponse(resultsResponse: ResultsResponse?): Results? {
        if(resultsResponse?.id == null) return null
        return Results(
            id = resultsResponse.id,
            name = resultsResponse.name.orEmpty(),
            status = resultsResponse.status.orEmpty(),
            species = resultsResponse.species.orEmpty(),
            type = resultsResponse.type.orEmpty(),
            gender = resultsResponse.gender.orEmpty(),
            origin = mapOrigin(resultsResponse.origin),
            location = mapLocation(resultsResponse.location),
            image = resultsResponse.image.orEmpty(),
            episode = resultsResponse.episode?.mapNotNull { mapEpisodes(it) } ?: listOf(),
            url = resultsResponse.url.orEmpty(),
            created = resultsResponse.created.orEmpty()
        )
    }

    private fun mapLocation(locationResponse: LocationResponse?): Location {
        return Location(
            name = locationResponse?.name.orEmpty(),
            url = locationResponse?.url.orEmpty()
        )
    }

    private fun mapOrigin(originResponse: OriginResponse?): Origin {
        return Origin(
            name = originResponse?.name.orEmpty(),
            url = originResponse?.url.orEmpty()
        )
    }

    private fun mapEpisodes(episodes: String?): String {
        return episodes.orEmpty()
    }
}