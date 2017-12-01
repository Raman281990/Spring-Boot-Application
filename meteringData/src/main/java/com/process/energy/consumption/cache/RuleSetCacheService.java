package com.process.energy.consumption.cache;

import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;

/**
 * @author GAGAN
 * 
 *RuleSetCacheService contains CRUD operations for Rule set Cache
 */
public interface RuleSetCacheService {

	/** addRuleSetIntoCache adds rule set to cache
	 * @param createRuleSetCache
	 * @return
	 */
	public boolean addRuleSetIntoCache(
			CreateOrUpdateRuleSetRequest createRuleSetCache);
	
	/**fetchRuleSetFromCache fetches rule set from cache
	 * @param fetchRuleSetRequest
	 * @return
	 */
	public String fetchRuleSetFromCache(FetchRuleSetRequest fetchRuleSetRequest);
	
	/** evictRuleSetFromCache removes rule set from cache
	 * @return
	 */
	public boolean evictRuleSetFromCache();
	
	/**updateRuleSetIntoCache updates rule set placed in cache.
	 * @param upateRuleSetCache
	 * @return
	 */
	public boolean updateRuleSetIntoCache(
			CreateOrUpdateRuleSetRequest upateRuleSetCache);

}
