package ch.joelniklaus.ecogame.controller.service;

import ch.joelniklaus.ecogame.controller.pojos.SearchForm;
import ch.joelniklaus.ecogame.model.Ad;
import ch.joelniklaus.ecogame.model.Search;
import ch.joelniklaus.ecogame.model.User;

public interface SearchService {
	
	/**
	 * Returns relevant search results.
	 * 
	 * @param searchForm
	 * @return Iterable<Ad>
	 */
	public Iterable<Ad> computeSearchResults(SearchForm searchForm);

    /**
     * Saves searchForm
     * 
     * @param searchForm			filled in SearchForm
     * @return Search
     */
	public Search saveSearch(SearchForm searchForm);
        
    /**
     * Returns saved searches from given user
     * 
     * @param user
     * @return Iterable<Search>
     */
	public Iterable<Search> findSearches(User user);
    
    /**
     * Looks if the search is already saved
     * 
     * @param user
     * @param searchId
     * @return boolean
     */
	public boolean alreadySaved(User user, Long searchId);
    
    /**
     * Removes search
     * 
     * @param search
     * @return Search
     */
	public Search removeSearch(Search search);

	

    
}
