package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader;

import java.net.MalformedURLException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;

public class PagedListLoader {
    public static PagedMovieListTask loadList(ListType type, int page, String language) throws MalformedURLException {
        switch(type)
        {
            case POPULAR:
                return API.loadPopularList(page, language);
            case TRENDING:
                return API.loadTrendingList(page, language);
            case UPCOMING:
                return API.loadUpcoming(page, language);
            case TOP_RATED:
                return API.loadTopRated(page, language);
            case NOW_PLAYING:
                return API.loadNowPlayingList(page, language);
        }
        return null;
    }
}
