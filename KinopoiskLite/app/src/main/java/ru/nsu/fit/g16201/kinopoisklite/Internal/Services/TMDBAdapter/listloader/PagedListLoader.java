package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;

public class PagedListLoader {
    public PagedMovieListTask loadList(ListType type, int page, String language)
    {
        switch(type)
        {
            case POPULAR:
                break;
            case TRENDING:
                break;
            case UPCOMING:
                break;
            case TOP_RATED:
                break;
            case NOW_PLAYING:
                break;
        }
        return null;
    }
}
