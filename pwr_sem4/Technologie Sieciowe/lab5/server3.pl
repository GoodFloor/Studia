#!/usr/bin/perl
use HTTP::Daemon;
use HTTP::Status;  
#use IO::File;
my $d = HTTP::Daemon->new || die;

print "Please contact me at: <URL:", $d->url, ">\n";
while (my $c = $d->accept) {
    while (my $r = $c->get_request) {
        # print "Requested file: ", $r->uri->path, "\n";
        # print "Request: ", $r->header("User-Agent"), "\n";
        # $c->send_header("User-Agent", $r->header("User-Agent"));
        if ($r->method eq 'GET' and $r->uri->path eq "/") {
            $file_s= "./index.html";    # index.html - jakis istniejacy plik
            $c->send_file_response($file_s);
        }
        elsif ($r->method eq 'GET') {
            $uri = $r->uri->path;
            $file_s =  ".${uri}";
            $c->send_file_response($file_s);
        }
        else {
            $c->send_error(RC_FORBIDDEN)
        }
    }
    $c->close;
    undef($c);
}
