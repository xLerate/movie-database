package de.ronald.movies.modellistener;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

public class AssetEntryModelListener implements ModelListener<AssetEntry> {

	Log _log = LogFactoryUtil.getLog(AssetEntryModelListener.class);
	
	
	private Log getLog() {
		return _log;
	}
	
	@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		if (AssetCategory.class.getName().equals(associationClassName)) {
			try {
				AssetCategory cat = AssetCategoryLocalServiceUtil.fetchAssetCategory((Long)associationClassPK);
				if ("Comic".equals(cat.getName())) {
					getLog().info("added 'comic' category");
					AssetEntry asset = AssetEntryLocalServiceUtil.fetchAssetEntry((Long)classPK);
					//JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimKey)
					if (asset != null && JournalArticle.class.getName().equals(asset.getClassName())) {
						JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(asset.getClassPK());
						if (article != null) {
							long companyId = PortalUtil.getDefaultCompanyId();
							String companyIdString = String.valueOf(companyId);
							Role contentReviewer = RoleLocalServiceUtil.getRole(companyId, RoleConstants.PORTAL_CONTENT_REVIEWER);
				            ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, JournalArticle.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(asset.getClassPK()), contentReviewer.getRoleId(), new String[0] );

						}
					}
					
				}
			} catch (SystemException | PortalException e) {
			} 
		}
		getLog().info("added assoc");
	}

	@Override
	public void onAfterCreate(AssetEntry model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAfterRemove(AssetEntry model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		if (AssetCategory.class.getName().equals(associationClassName)) {
			try {
				AssetCategory cat = AssetCategoryLocalServiceUtil.fetchAssetCategory((Long)associationClassPK);
				if ("Comic".equals(cat.getName())) {
					getLog().info("removed 'comic' category");
					AssetEntry asset = AssetEntryLocalServiceUtil.fetchAssetEntry((Long)classPK);
					
					if (asset != null && JournalArticle.class.getName().equals(asset.getClassName())) {
						JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(asset.getClassPK());
						if (article != null) {
							long companyId = PortalUtil.getDefaultCompanyId();
							String companyIdString = String.valueOf(companyId);
							Role contentReviewer = RoleLocalServiceUtil.getRole(companyId, RoleConstants.PORTAL_CONTENT_REVIEWER);
				            ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, JournalArticle.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(asset.getClassPK()), contentReviewer.getRoleId(), new String[] { "VIEW"});
						}
					}
					
				}
			} catch (SystemException | PortalException e) {
			} 
		}
		
		
		getLog().info("removed assoc");
	}

	@Override
	public void onAfterUpdate(AssetEntry model) throws ModelListenerException {
		getLog().info("after update");
		
	}

	@Override
	public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeCreate(AssetEntry model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeRemove(AssetEntry model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeUpdate(AssetEntry model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

}
