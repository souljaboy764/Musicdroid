/*
 * Musicdroid: An on-device music generator for Android
 * Copyright (C) 2010-2014 The Catrobat Team
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

package org.catrobat.musicdroid.pocketmusic.test.note.symbol;

import android.test.AndroidTestCase;

import org.catrobat.musicdroid.pocketmusic.note.Track;
import org.catrobat.musicdroid.pocketmusic.note.symbol.Symbol;
import org.catrobat.musicdroid.pocketmusic.note.symbol.SymbolsToTrackConverter;
import org.catrobat.musicdroid.pocketmusic.test.note.TrackTestDataFactory;

import java.util.List;

public class SymbolsToTrackConverterTest extends AndroidTestCase {

    public void testConvertSymbols() {
        SymbolsToTrackConverter converter = new SymbolsToTrackConverter();
        List<Symbol> symbols = TrackToSymbolsConverterTest.createSymbolListWithBreak();
        Track expectedTrack = TrackTestDataFactory.createTrackWithBreak();

        assertEquals(expectedTrack, converter.convertSymbols(symbols, expectedTrack.getKey(), expectedTrack.getInstrument(), expectedTrack.getBeatsPerMinute()));
    }
}
