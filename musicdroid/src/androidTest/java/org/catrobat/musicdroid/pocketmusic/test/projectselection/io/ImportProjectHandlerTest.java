/*
 * Musicdroid: An on-device music generator for Android
 * Copyright (C) 2010-2015 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.musicdroid.pocketmusic.test.projectselection.io;

import android.app.Activity;
import android.content.Intent;
import android.test.AndroidTestCase;

import org.catrobat.musicdroid.pocketmusic.note.Project;
import org.catrobat.musicdroid.pocketmusic.note.midi.MidiException;
import org.catrobat.musicdroid.pocketmusic.note.midi.ProjectToMidiConverter;
import org.catrobat.musicdroid.pocketmusic.projectselection.io.ImportProjectHandler;
import org.catrobat.musicdroid.pocketmusic.test.note.ProjectTestDataFactory;

import java.io.File;
import java.io.IOException;

public class ImportProjectHandlerTest extends AndroidTestCase {

    private String projectName = "Bimbo";
    private ImportProjectHandlerMock handler;

    @Override
    protected void setUp() {
        handler = new ImportProjectHandlerMock();
    }

    @Override
    protected void tearDown() throws IOException {
        ProjectToMidiConverter.getMidiFileFromProjectName(projectName).delete();
    }

    public void testStartIntent() {
        Intent intent = new Intent();
        int resultCode = 42;

        handler.startIntent(intent, resultCode);

        assertEquals(intent, handler.getIntent());
        assertEquals(resultCode, handler.getResultCode());
    }

    public void testImportProject() throws IOException, MidiException {
        ProjectToMidiConverter converter = new ProjectToMidiConverter();
        File file = new File(ProjectToMidiConverter.MIDI_FOLDER, projectName);

        converter.writeProjectAsMidi(ProjectTestDataFactory.createProject(), file);
        handler.importProject(ImportProjectHandler.PICKFILE_RESULT_CODE, Activity.RESULT_OK, file);

        assertTrue(ProjectToMidiConverter.getMidiFileFromProjectName(projectName).exists());
    }

    public void testImportProjectFailed() throws IOException, MidiException {
        ProjectToMidiConverter converter = new ProjectToMidiConverter();

        Project project = ProjectTestDataFactory.createProject(projectName);
        converter.writeProjectAsMidi(project);

        try {
            handler.importProject(ImportProjectHandler.PICKFILE_RESULT_CODE, Activity.RESULT_OK, ProjectToMidiConverter.getMidiFileFromProjectName(projectName));
            assertTrue(false);
        } catch (Exception ignored) {
        }
    }
}
