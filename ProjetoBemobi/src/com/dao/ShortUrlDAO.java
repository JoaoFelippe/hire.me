package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connectionFactory.ConnectionFactory;

public class ShortUrlDAO {

	public void Closeconnection(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void Closeconnection(Connection con, PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<String[]> RetornarListaAlias() {
		Connection con = new ConnectionFactory().getConnection();
		List<String[]> listUrls = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from Encurtador");
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				String shortUrl = rs.getString("shortUrl");
				String array[] = {("" + id) ,url, shortUrl };
				listUrls.add(array);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Closeconnection(con, ps, rs);

		return listUrls;
	}

	public String[] buscaAlias(String alias) {
		Connection con = new ConnectionFactory().getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] url_alias = new String[2];
		try {
			ps = con.prepareStatement("select * from Encurtador where shortUrl=?");
			ps.setString(1, alias);
			rs = ps.executeQuery();
			while (rs.next()) {
				url_alias[0] = rs.getString("url");
				url_alias[1] = rs.getString("shortUrl");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closeconnection(con, ps, rs);
		return url_alias;

	}
	public String[] buscaID(String id) {
		Connection con = new ConnectionFactory().getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] url_alias = new String[2];
		try {
			ps = con.prepareStatement("select * from Encurtador where id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				url_alias[0] = rs.getString("url");
				url_alias[1] = rs.getString("shortUrl");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closeconnection(con, ps, rs);
		return url_alias;

	}

	public void insertAlias(String url, String alias,int id) {
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement ps = null;

		String sql = "insert into Encurtador " + "(id,url,shortUrl)" + "values (?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, url);
			ps.setString(3, alias);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closeconnection(con, ps);

	}


}
