package com.ibookstore.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UITools {

	public static void openAlertDialogWithOKButton(Context context,
			String title, String message) {

		new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton("OK",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(
									DialogInterface paramDialogInterface,
									int paramInt) {

							}
						}).show();
	}
}
