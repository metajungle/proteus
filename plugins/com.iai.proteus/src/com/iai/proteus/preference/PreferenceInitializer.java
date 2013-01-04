package com.iai.proteus.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.iai.proteus.Activator;
import com.iai.proteus.PreferenceConstants;

/**
 * Initializes the preference store
 *
 * @author jhenriksson
 *
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * Constructor
	 *
	 */
	public PreferenceInitializer() {
		super();
	}

	/**
	 * Sets defaults values
	 */
	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// defaults
		store.setDefault(PreferenceConstants.prefCommunityHub,
				"http://api.i-a-i.com/proteus/");
		store.setDefault(PreferenceConstants.prefConnectionTimeout, 10);
		store.setDefault(PreferenceConstants.prefConnectionReadTimeout, 60);
	}

}
