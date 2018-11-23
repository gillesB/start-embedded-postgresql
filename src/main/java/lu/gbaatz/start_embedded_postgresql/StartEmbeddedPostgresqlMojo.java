/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lu.gbaatz.start_embedded_postgresql;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.maven.plugin.*;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.*;

import com.opentable.db.postgres.embedded.*;

/**
 * Goal which starts an embedded postgresql instance
 *
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.VALIDATE)
public class StartEmbeddedPostgresqlMojo extends AbstractMojo {

	@Parameter(defaultValue = "src/main/resources/db/migration", property = "flywayDir", required = true)
	private File flywayDirectory;

	@Parameter(defaultValue = "${project}")
	private MavenProject project;

	public void execute() throws MojoExecutionException {
		FlywayPreparer preparer = FlywayPreparer
				.forClasspathLocation("filesystem:" + flywayDirectory.getAbsolutePath());
		PreparedDbProvider dbProvider = PreparedDbProvider.forPreparer(preparer);
		try {
			ConnectionInfo conInfo = dbProvider.createNewDatabase();
			Properties properties = project.getProperties();
			properties.setProperty("pgHost", "localhost");
			properties.setProperty("pgPort", Integer.toString(conInfo.getPort()));
			properties.setProperty("pgDbName", conInfo.getDbName());
			properties.setProperty("pgUser", conInfo.getUser());
			properties.setProperty("pgPw", "postgres");
		} catch (SQLException e) {
			throw new MojoExecutionException("The embedded database could not be generated.", e);
		}
	}
}
