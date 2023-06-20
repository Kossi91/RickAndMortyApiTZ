package com.example.rickandmortyapitz.presentation.models

sealed class RickyAndMortyModelUI {

    class CharactersModelUI(val charactersModel: CharacterModelUI) : RickyAndMortyModelUI()
    class EpisodesModelUI(val episodeModel: EpisodeModelUI) : RickyAndMortyModelUI()
    class LocationsModelUI(val locationModel: LocationModelUI) : RickyAndMortyModelUI()
}

fun CharacterModelUI.toCharacterModelUI() = RickyAndMortyModelUI.CharactersModelUI(
    CharacterModelUI(id, name, image, status, species, location, type, episode, gender, created)
)

fun LocationModelUI.toLocationModelUI() = RickyAndMortyModelUI.LocationsModelUI(
    LocationModelUI(id, name, type, dimension, created)
)

fun EpisodeModelUI.toEpisodeModelUI() = RickyAndMortyModelUI.EpisodesModelUI(
    EpisodeModelUI(id, name, episode, air_date, created)
)
